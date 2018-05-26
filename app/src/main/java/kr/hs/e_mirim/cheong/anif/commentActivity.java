package kr.hs.e_mirim.cheong.anif;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class commentActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;

    private FirebaseUser mFirebaseUser;

    private FirebaseAuth mFirebaseAuth;

    private ListView listview;

    ListView listView;
    MyListAdapter myListAdapter;
    ArrayList<ListItem> list_itemArrayList;

    InputMethodManager imm;
    Button btnComment;
    EditText editComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        listView = (ListView)findViewById(R.id.list);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

//        final ArrayList<String> items = new ArrayList<String>() ;
//        final ArrayAdapter adapter = new kr.hs.e_mirim.cheong.anif.ListAdapter(this,items);
        list_itemArrayList = new ArrayList<ListItem>();

        // listview 생성 및 adapter 지정.
//        listview = (ListView) findViewById(R.id.list) ;
//        listview .setDivider(null);
        myListAdapter = new MyListAdapter(commentActivity.this,list_itemArrayList);
        listView.setAdapter(myListAdapter);

        btnComment = (Button)findViewById(R.id.set_comment);
        editComment = (EditText)findViewById(R.id.edit_comment);

        mFirebaseDatabase.getReference("comments/")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Comment comment = dataSnapshot.getValue(Comment.class);
                        comment.setKey(dataSnapshot.getKey());
                        // 아이템 추가.
                        list_itemArrayList.add(new ListItem(comment.getName(), comment.getTxt(), comment.getCreateDate()));

                        // listview 갱신
                        myListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        Comment comment = dataSnapshot.getValue(Comment.class);
                        comment.setKey(dataSnapshot.getKey());
                        // 아이템 추가.
                        list_itemArrayList.add(new ListItem(comment.getName(), comment.getTxt(), comment.getCreateDate()));

                        // listview 갱신
                        myListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editComment.getText().toString() == null){
                    Toast.makeText(getApplicationContext(), "댓글을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    hideKeyboard();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
                    Date currentTime = new Date();
                    String dTime = formatter.format(currentTime);
                    Comment comment = new Comment();
                    comment.setName(mFirebaseUser.getDisplayName());
                    comment.setTxt(editComment.getText().toString());
                    comment.setCreateDate(dTime);
                    Toast.makeText(getApplicationContext(), "댓글 추가중", Toast.LENGTH_SHORT).show();
                    mFirebaseDatabase
                            .getReference("comments/")
                            .push()
                            .setValue(comment)
                            .addOnSuccessListener(commentActivity.this, new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "댓글 추가 완료", Toast.LENGTH_SHORT).show();
                                    editComment.setText("");
                                }
                            });
                    }
                }
            }
        );
    }

    private void hideKeyboard()
    {
        imm.hideSoftInputFromWindow(editComment.getWindowToken(), 0);
    }
}


