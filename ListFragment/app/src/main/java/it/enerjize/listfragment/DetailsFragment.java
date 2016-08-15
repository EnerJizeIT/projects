package it.enerjize.listfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailsFragment extends Fragment {
    private ImageView image;
    private TextView name;
    private TextView phone;
    private TextView email;


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        image = (ImageView)view.findViewById(R.id.detailsPhoto);
        name = (TextView)view.findViewById(R.id.detailsName);
        phone = (TextView)view.findViewById(R.id.detailsPhone);
        email = (TextView)view.findViewById(R.id.detailsEmail);

        return  view;
    }
    public void refreshContactDetails(ContactItem item){
        image.setImageResource(item.getPhotoId());
        name.setText(item.getName());
        phone.setText(item.getPhone());
        email.setText(item.getEmail());
    }

}
