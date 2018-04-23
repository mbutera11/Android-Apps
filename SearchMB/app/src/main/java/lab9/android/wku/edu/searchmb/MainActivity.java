// Michael Butera

package lab9.android.wku.edu.searchmb;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    protected EditText query;
    protected Button search;
    protected WebView wv;
    protected TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        query = findViewById(R.id.searchQuery);
        search = findViewById(R.id.search);
        wv = findViewById(R.id.webView);

        // allows web search on same activity
        wv.setWebViewClient(new WebViewClient());

        // when button is clicked
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // text for edit text
                final String searchText = query.getText().toString();

                // if text is empty, toast message to enter something
                // else, continue search
                if(searchText.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter a search", Toast.LENGTH_LONG).show();
                } else {
                    // initialize text to speach object and set listener
                    textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            // if there is no error, set language to english and tell it to speak
                            if(status != TextToSpeech.ERROR) {
                                textToSpeech.setLanguage(Locale.ENGLISH);
                                textToSpeech.speak("You are searching " + searchText, TextToSpeech.QUEUE_FLUSH, null);
                            }
                        }
                    });

                    // google search url with query
                    String url = "http://www.google.com/#q=" + searchText;
                    wv.getSettings().setLoadsImagesAutomatically(true);
                    wv.getSettings().setJavaScriptEnabled(true);
                    wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

                    // load url in the webview
                    wv.loadUrl(url);
                }
            }
        });


    }
}
