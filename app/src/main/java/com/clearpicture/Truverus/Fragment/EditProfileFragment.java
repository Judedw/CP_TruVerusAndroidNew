package com.clearpicture.Truverus.Fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.clearpicture.Truverus.R;
import com.clearpicture.Truverus.util.TagContainerLayout;
import com.clearpicture.Truverus.util.TagView;
import com.clearpicture.Truverus.util.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import id.zelory.compressor.Compressor;
import me.drakeet.materialdialog.MaterialDialog;

import static android.app.Activity.RESULT_OK;
import static android.support.v4.graphics.TypefaceCompatUtil.getTempFile;
import static com.facebook.login.widget.ProfilePictureView.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment implements View.OnClickListener {
    private EditText txtBirthday;
    private Date selectedDate;
    private TagContainerLayout tagcontainerLayout;
    private TagContainerLayout fashionTagcontainerLayout;
    private Button favorites_sportsbtn_add_tag;
    private EditText favorites_sports_text_tag;
    private Button favorites_fashionb_addbtn_tag;
    private EditText favorites_fashiontext_tag;
    private ImageView caremaImg;
    private de.hdodenhof.circleimageview.CircleImageView profilePicImg;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private final int SELECT_PHOTO = 101;
    private final int CAPTURE_PHOTO = 102;
    final private int REQUEST_CODE_WRITE_STORAGE = 1;
    Uri uri;
    public EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        Spinner mySpinner = view.findViewById(R.id.spinner1);
        txtBirthday =  view.findViewById(R.id.txtBirthday);
        tagcontainerLayout = view.findViewById(R.id.tagcontainerLayout);
        fashionTagcontainerLayout = view.findViewById(R.id.fashionTagcontainerLayout);
        favorites_fashionb_addbtn_tag = view.findViewById(R.id.favorites_fashionb_addbtn_tag);
        favorites_sportsbtn_add_tag = view.findViewById(R.id.favorites_sportsbtn_add_tag);
        favorites_fashiontext_tag = view.findViewById(R.id.favorites_fashiontext_tag);
        favorites_sports_text_tag = view.findViewById(R.id.favorites_sports_text_tag);
        profilePicImg = view.findViewById(R.id.profilePicImg);
        caremaImg = view.findViewById(R.id.caremaImg);

        List<String> FavoriteSportsList = new ArrayList<String>();
        List<String> FavoriteFashionList = new ArrayList<String>();

        FavoriteSportsList.add("New Trends");
        FavoriteFashionList.add("New Trends");

        favorites_sportsbtn_add_tag.setOnClickListener(this);
        favorites_fashionb_addbtn_tag.setOnClickListener(this);
        caremaImg.setOnClickListener(this);

        tagcontainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                if (position < tagcontainerLayout.getChildCount()) {
                    tagcontainerLayout.removeTag(position);
                }
            }

            @Override
            public void onTagLongClick(final int position, String text) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("long click")
                        .setMessage("You will delete this tag!")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (position < tagcontainerLayout.getChildCount()) {
                                    tagcontainerLayout.removeTag(position);
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }

            @Override
            public void onSelectedTagDrag(int position, String text) {
            }

            @Override
            public void onTagCrossClick(int position) {
                tagcontainerLayout.removeTag(position);

            }
        });

        favorites_sports_text_tag.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    tagcontainerLayout.addTag(favorites_sports_text_tag.getText().toString());
                    favorites_sports_text_tag.setText("");
                }
                return false;
            }
        });

        tagcontainerLayout.setTags(FavoriteSportsList);

        fashionTagcontainerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                if (position < fashionTagcontainerLayout.getChildCount()) {
                    fashionTagcontainerLayout.removeTag(position);
                }
            }

            @Override
            public void onTagLongClick(final int position, String text) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("long click")
                        .setMessage("You will delete this tag!")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (position < fashionTagcontainerLayout.getChildCount()) {
                                    fashionTagcontainerLayout.removeTag(position);
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }

            @Override
            public void onSelectedTagDrag(int position, String text) {
            }

            @Override
            public void onTagCrossClick(int position) {
                fashionTagcontainerLayout.removeTag(position);

            }
        });

        favorites_fashiontext_tag.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    fashionTagcontainerLayout.addTag(favorites_fashiontext_tag.getText().toString());
                    favorites_fashiontext_tag.setText("");
                }
                return false;
            }
        });

        fashionTagcontainerLayout.setTags(FavoriteFashionList);


        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    System.out.print("one ");
                } else if (i == 2) {
                    System.out.print("two ");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        txtBirthday.setOnClickListener(this);

        return view;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtBirthday:
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), Selecteddate, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH));
//                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000);
                    datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

                    datePickerDialog.show();
                }
                break;
            case R.id.favorites_sportsbtn_add_tag :{
                if (!favorites_sports_text_tag.getText().toString() .equals("") ){
                    tagcontainerLayout.addTag(favorites_sports_text_tag.getText().toString());
                    favorites_sports_text_tag.setText("");
                }

            }
            break;

            case R.id.favorites_fashionb_addbtn_tag :{
                if(!favorites_fashiontext_tag.getText().toString() .equals("") ){
                    fashionTagcontainerLayout.addTag(favorites_fashiontext_tag.getText().toString());
                    favorites_fashiontext_tag.setText("");
                }
            }
            break;
            case R.id.caremaImg :{
                int hasWriteStoragePermission = 0;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//                    hasWriteStoragePermission = checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }

                if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_CODE_WRITE_STORAGE);
                    }
                    //return;
                }

                listDialogue();
            }
            break;

        }
    }
    public void listDialogue(){
        final ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);

        arrayAdapter.add("Take Photo");
        arrayAdapter.add("Select Gallery");

        ListView listView = new ListView(getActivity());
        listView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (8 * scale + 0.5f);
        listView.setPadding(0, dpAsPixels, 0, dpAsPixels);
        listView.setDividerHeight(0);
        listView.setAdapter(arrayAdapter);

        final MaterialDialog alert = new MaterialDialog(getActivity()).setContentView(listView);

        alert.setPositiveButton("Cancel", new View.OnClickListener() {
            @Override public void onClick(View v) {
                alert.dismiss();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                    alert.dismiss();
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //Uri uri  = Uri.parse("file:///sdcard/photo.jpg");
                    String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "propic.jpg";
                    uri = Uri.parse(root);
                    //i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(i, CAPTURE_PHOTO);

                }else {

                    alert.dismiss();
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);

                }
            }
        });

        alert.show();

    }



    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener Selecteddate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "dd MMM yyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            txtBirthday.setText(String.valueOf(sdf.format(myCalendar.getTime())));
            selectedDate = myCalendar.getTime();
            SimpleDateFormat mdformat = new SimpleDateFormat("yyyy/MM/dd ");
            Log.d(TAG, "onDateSet: " + mdformat.format(myCalendar.getTime()) + ":" + mdformat.format(Calendar.getInstance().getTime()));


            if (mdformat.format(myCalendar.getTime()).equals(mdformat.format(Calendar.getInstance().getTime()))) {
                final Calendar current = Calendar.getInstance();
                int hour = current.get(Calendar.HOUR_OF_DAY);
                final int minute = current.get(Calendar.MINUTE);
                Log.d(TAG, "onDateSet: time: " + Calendar.getInstance().getTime().getTime() + ":");


            }


        }
    };
    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap( v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getWidth(), v.getHeight());
        v.draw(c);
        return b;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {

                    Uri imageUri = imageReturnedIntent.getData();
                    profilePicImg.setImageURI(imageUri);

//                    String selectedImagePath = getPath(imageUri);
//                    File f = new File(selectedImagePath);
//                    final Bitmap bmp = Compressor.getDefault(getActivity()).compressToBitmap(f);

//                            profilePicImg.setImageBitmap(bmp);

                }
                break;

            case CAPTURE_PHOTO:
                if (resultCode == RESULT_OK) {

                    Bitmap bmp = imageReturnedIntent.getExtras().getParcelable("data");

                    profilePicImg.setImageBitmap(bmp);
                }

                break;
        }
    }

    public String getPath(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContext().getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
}
