package com.example.enerjizeit.nerdlauncher;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/* Для того чтобы приложение запускалось в качестве стандартного лаунчера
<category android:name="android.intent.category.HOME" />
<category android:name="android.intent.category.DEFAULT" /> */
public class NerdLauncherFragment extends Fragment {
    public NerdLauncherFragment() {
    }

    public static NerdLauncherFragment newInstance() {
        return new NerdLauncherFragment();
    }

    private RecyclerView recyclerView;
    private static final String TAG = "com.example.enerjizeit.nerdlauncher";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nerd_launcher, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();
        return view;
    }

    private void setupAdapter() {
        Intent startupIntent = new Intent(Intent.ACTION_MAIN);
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager packgeManager = getActivity().getPackageManager();
        List<ResolveInfo> activities = packgeManager.queryIntentActivities(startupIntent, 0);
        Collections.sort(activities, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo lhs, ResolveInfo rhs) {
                PackageManager pm = getActivity().getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(
                        lhs.loadLabel(pm).toString(),
                        rhs.loadLabel(pm).toString());
            }
        });

        recyclerView.setAdapter(new ActivityAdapter(activities));
        L.l("Found " + activities.size() + " activities.");
    }

    private class ActivityHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener {
        private ResolveInfo resolveInfo;
        private TextView textView;
        private ImageView imageView;

        public ActivityHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.titleView);
            imageView = (ImageView)itemView.findViewById(R.id.iconView);
        }

        public void bindActivity(ResolveInfo resolveInfo) {
            this.resolveInfo = resolveInfo;
            PackageManager pm = getActivity().getPackageManager();
            String appName = resolveInfo.loadLabel(pm).toString();
            Drawable icon = resolveInfo.loadIcon(pm);
            textView.setText(appName);
            imageView.setImageDrawable(icon);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ActivityInfo actiInfo = resolveInfo.activityInfo;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClassName(actiInfo.applicationInfo.packageName, actiInfo.name);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/* Чтобы при запуске новой активности запускалась
 новая задача, следует добавить в интент соответствующий флаг */
            startActivity(intent);
        }
    }

    private class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder> {
        private final List<ResolveInfo> activities;

        private ActivityAdapter(List<ResolveInfo> activities) {
            this.activities = activities;
        }

        @Override
        public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.item, parent, false);
            return new ActivityHolder(view);
        }

        @Override
        public void onBindViewHolder(ActivityHolder holder, int position) {
            ResolveInfo resolveInfo = activities.get(position);
            holder.bindActivity(resolveInfo);
        }

        @Override
        public int getItemCount() {
            return activities.size();
        }
    }
}
