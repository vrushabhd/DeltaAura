package com.deltaaura.app;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactusFragement extends Fragment {
   private Button PostMessage;
   private EditText name,email,subject,message;
    private WebView webView;
    public static String weburlHome ="https://www.deltatheinnovators.com/contact";

    public ContactusFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
           View view =inflater.inflate(R.layout.fragment_contactus_fragement, container, false);
//         PostMessage  =view.findViewById(R.id.post_message);
//        name =view.findViewById(R.id.your_name);
//        email =view.findViewById(R.id.your_email);
//        subject =view.findViewById(R.id.your_subject);
//        message  = view.findViewById(R.id.your_message);
        webView =view.findViewById(R.id.contact);
        webView.setBackgroundColor(Color.BLACK);
//       PostMessage.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               String username = name.getText().toString().trim();
//               String usermail =email.getText().toString().trim();
//               String usersubject  = subject.getText().toString().trim();
//               String userMessage = message.getText().toString().trim();
//               if(usermail.isEmpty()){
//                   email.setError("Email is Required");
//                   email.requestFocus();//focus goes to required input text
//                   return;//Exiting OnclickListener
//
//               }
//
//               if(!Patterns.EMAIL_ADDRESS.matcher(usermail).matches()){
//
//                   email.setError("Valid Email is Required");
//                   email.requestFocus();//focus goes to required input text
//                   return;//Exiting OnclickListener
//
//
//
//               }
//               if(TextUtils.isEmpty(username)){
//                   name.setError("Name is Required");
//                   name.requestFocus();//focus goes to required input text
//                   return;//Exiting OnclickListener
//
//               }
//
//
//
//               if(TextUtils.isEmpty(usersubject)){
//                   subject.setError("Subject is Required");
//                   subject.requestFocus();//focus goes to required input text
//                   return;//Exiting OnclickListener
//
//               }
//
//
//               if(TextUtils.isEmpty(userMessage)){
//                   message.setError("Messagee is Required");
//                   message.requestFocus();//focus goes to required input text
//                   return;//Exiting OnclickListener
//
//               }
//
//        Intent sendEmail =new Intent(Intent.ACTION_SEND);
//               //sendEmail.setData(Uri.parse("mailto:"));
//               sendEmail.setType("message/rfc822");
//           //    sendEmail.setDataAndType(Uri.parse("mailto:"),"message/rfc822");
//                String to = "deltatheinnovators@gmail.com";
//               sendEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
//               sendEmail.putExtra(Intent.EXTRA_SUBJECT,usersubject);
//            //   sendEmail.putExtra(Intent.EXTRA_TEXT,userMessage);
//               sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
//                       "name:"+username+'\n'+'\n'+"Message: "+'\n'+userMessage);
//
//               try {
//                       startActivity(Intent.createChooser(sendEmail, "Choose an Email Client"));
//                   } catch (android.content.ActivityNotFoundException ex) {
//                       Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//                   }
//
//
//
//
//
//
//
//
//
//
//           }
//       });
//
//

        webView.loadUrl(weburlHome);
        WebSettings webSettings =webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        return  view;







    }

}
