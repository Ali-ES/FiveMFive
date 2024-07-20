package ir.FiveMFive.FiveMFive.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ir.FiveMFive.FiveMFive.Activity.LoginActivity;
import ir.FiveMFive.FiveMFive.Java.CategoryItem;
import ir.FiveMFive.FiveMFive.R;
import ir.FiveMFive.FiveMFive.RecyclerViewAdapter;
import ir.FiveMFive.FiveMFive.Utility.CredentialCrypter;
import ir.FiveMFive.FiveMFive.Utility.PopupBuilder;
import ir.FiveMFive.FiveMFive.Utility.ToolbarHandler;

public class CategoryFragment extends Fragment {
    private static final String KEY_CATEGORY_TYPE = "categoryType";
    private static final RecyclerViewAdapter.LayoutType CATEGORY_LAYOUT_TYPE = RecyclerViewAdapter.LayoutType.CATEGORY;
    private View v;
    private Context c;
    private Toolbar toolbar;
    private RecyclerView categoryRecycle;
    private CategoryType categoryType;

    public enum CategoryType {
        SEND_MESSAGE
    }

    public static CategoryFragment newInstance(CategoryType type) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CATEGORY_TYPE, type);

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments != null) {
            categoryType = (CategoryType) arguments.getSerializable(KEY_CATEGORY_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        this.v = v;
        c = requireContext();

        categoryRecycle = v.findViewById(R.id.category_recycle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        categoryRecycle.setLayoutManager(layoutManager);

        toolbar = v.findViewById(R.id.toolbar);

        ConstraintLayout toolbarLayout = toolbar.findViewById(R.id.toolbar_layout);
        ImageView more = toolbarLayout.findViewById(R.id.more_iv);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupBuilder popupBuilder = new PopupBuilder(getContext());
                popupBuilder.addItem(R.drawable.ic_exit, R.string.exit, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CredentialCrypter.removeSavedCredentials(requireContext());
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        requireActivity().finish();
                        popupBuilder.dismiss();
                    }
                });
                popupBuilder.showPopup(toolbar);
            }
        });

        if(categoryType != null) {
            switch (categoryType) {
                case SEND_MESSAGE:
                    String[] sendMessageItems = getResources().getStringArray(R.array.array_send_message);
                    ArrayList<CategoryItem> categoryItems = new ArrayList<>();
                    for (int i = 0; i < sendMessageItems.length; i++) {
                        categoryItems.add(new CategoryItem(sendMessageItems[i], i));
                    }

                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, CATEGORY_LAYOUT_TYPE, categoryItems);
                    categoryRecycle.setAdapter(adapter);

//                    TextView titleText = (TextView) toolbarLayout.findViewById(R.id.title_tv);
//                    titleText.setText(getText(R.string.send_message));
//                    break;
            }
        }

        getResources().getStringArray(R.array.array_send_message);


        return v;
    }


}