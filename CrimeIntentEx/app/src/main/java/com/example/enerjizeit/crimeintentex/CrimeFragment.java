package com.example.enerjizeit.crimeintentex;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment {
    private static final String ARGUMENT = "crime_id";
    private static final String DATE_TAG = "date_tag";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;

    private Crime crime;
    private EditText titleField;
    private Button dateButton, suspectButton;
    private CheckBox solvedCheckBox;

    public CrimeFragment() {
    }

    public static CrimeFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(ARGUMENT, id);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID) getArguments().getSerializable(ARGUMENT);
        crime = CrimeLab.getCrimeLab(getActivity()).getCrime(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        /* Второйпараметр определяет родителя представления, что обычно необходимо для правильной настройки виджета.
 Третий параметр указывает, нужно ли включать заполненное представление в родителя. Мы передаем false,
  потому что представление будет добавлено в коде активности. */
        dateButton = (Button) view.findViewById(R.id.crime_date);
        updateDate();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(crime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DATE_TAG);
            }
        });

        solvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);
        solvedCheckBox.setChecked(crime.isSolved());
        solvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crime.setSolved(isChecked);
            }
        });

        titleField = (EditText) view.findViewById(R.id.crime_title);
        titleField.setText(crime.getTitle());
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                crime.setTitle(s.toString());
                /* Мы добавили слушателя для изменения текста */
            }
        });
        final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

        suspectButton = (Button) view.findViewById(R.id.choose_suspect);
        suspectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(pickContact, REQUEST_CONTACT);
            }
        });

        if (crime.getSuspect() != null) {
            suspectBntSetText();
        }

        PackageManager packageManager = getActivity().getPackageManager();
        if (packageManager.resolveActivity(pickContact, PackageManager.MATCH_DEFAULT_ONLY) == null) {
            suspectButton.setEnabled(false);
        }

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            crime.setDate(date);
            updateDate();
        } else if (requestCode == REQUEST_CONTACT && data != null) {
            Uri contactUri = data.getData();
            /* Определение полей поиска */
            String[] queryFields = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
            /* contactUri здесь выполняет функции условия "where" */
            Cursor cursor = getActivity().getContentResolver().query(contactUri, queryFields, null, null, null);

            try {
                if (cursor.getCount() == 0) {
                    return;
                }
                cursor.moveToFirst();
                String suspect = cursor.getString(0);
                crime.setSuspect(suspect);
                suspectBntSetText();
            } finally {
                cursor.close();
            }

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        CrimeLab.getCrimeLab(getActivity()).updateCrime(crime);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//
//        ShareCompat.IntentBuilder b = ShareCompat.IntentBuilder.from(getActivity());
//        b.setType("text/plain").setText(getCrimeReport());
//        MenuItem item = menu.add("Share");
//        ShareCompat.configureMenuItem(item, b);
//        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.action_social) {
//            ShareCompat.IntentBuilder intentBuilder = ShareCompat.IntentBuilder.from(getActivity());
//            intentBuilder.setChooserTitle("Choose Share App")
//                    .setType("text/plain")
//                    .setText("A flat button library for android #AndroidFlat goo.gl/C6aLDi")
//                    .startChooser();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void suspectBntSetText() {
        String suspect_is = getString(R.string.suspect_is) + crime.getSuspect();
        suspectButton.setText(suspect_is);
    }

    private void updateDate() {
        dateButton.setText(android.text.format.DateFormat.format("EEEE, dd MMMM yyyy", crime.getDate()).toString());
    }

    private String getCrimeReport() {
        String solvedString = null;
        if (crime.isSolved()) {
            solvedString = getString(R.string.crime_report_solved);
        } else {
            solvedString = getString(R.string.crime_report_unsolved);
        }
        String dateFormat = "dd MM yyyy";
        String dateString = DateFormat.format(dateFormat, crime.getDate()).toString();
        String suspect = crime.getSuspect();
        if (suspect == null) {
            suspect = getString(R.string.crime_report_no_suspect);
        } else {
            suspect = getString(R.string.crime_report_suspect, suspect);
        }
        /* <string name="crime_report">%1$s! The crime was discovered on %2$s. %3$s, and %4$s</string>
         Поля %1$s, %2$s и т. д. — заполнители для строковых аргументов. В коде вы вызываете getString(…)
          и передаете форматную строку и еще четыре строки в том порядке, в каком они должны заменять заполнители. */
        String report = getString(R.string.report,
                crime.getTitle(), dateString, solvedString, suspect);
        return report;
    }
}
