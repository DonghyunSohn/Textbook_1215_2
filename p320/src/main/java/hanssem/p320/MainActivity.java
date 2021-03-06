package hanssem.p320;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    TextView textView1;
    EditText edit01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 선택된 메뉴를 표시할 텍스트뷰
        textView1 = (TextView) findViewById(R.id.textView1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        View v = menu.findItem(R.id.menu_search).getActionView();
        if (v != null) {
            edit01 = (EditText) v.findViewById(R.id.edit01);

            if (edit01 != null) {
                edit01.setOnEditorActionListener(onSearchListener);
            }
        } else {
            Toast.makeText(getApplicationContext(), "ActionView is null.", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_refresh:  // 새로고침 메뉴 선택
                textView1.setText("새로고침 메뉴를 선택했습니다.");
                return true;

            case R.id.menu_search:  // 검색 메뉴 선택
                textView1.setText("검색 메뉴를 선택했습니다.");
                return true;

            case R.id.menu_settings:  // 설정 메뉴 선택
                textView1.setText("설정 메뉴를 선택했습니다.");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private TextView.OnEditorActionListener onSearchListener = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (event == null || event.getAction() == KeyEvent.ACTION_UP) {
                // 검색 메소드 호출
                search();

                // 키패드 닫기
                InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }

            return (true);
        }
    };

    private void search() {
        String searchString = edit01.getEditableText().toString();
        Toast.makeText(this, "검색어 : " + searchString, Toast.LENGTH_SHORT).show();
    }
}
