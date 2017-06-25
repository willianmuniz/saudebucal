package br.com.willmo.saudebucal.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import org.joda.time.LocalDateTime;

import br.com.willmo.saudebucal.R;
import br.com.willmo.saudebucal.entity.Day;
import br.com.willmo.saudebucal.entity.DayType;
import br.com.willmo.saudebucal.entity.Reminder;
import br.com.willmo.saudebucal.persistent.dao.DayDao;
import br.com.willmo.saudebucal.persistent.dao.ReminderDao;
import br.com.willmo.saudebucal.tools.Constants;

public class ReminderActivity extends Activity {

    public static String PARAM_SHOW_REMINDER = "PARAM_SHOW_REMINDER";

    //View objects
    private TextView reminderText1;
    private TextView reminderText2;
    private TextView reminderText3;
    private TextView reminderText4;
    private ImageView reminderImg1;
    private ImageView reminderImg2;
    private VideoView reminderVideo;
    private TextView reminderTextDown1;
    private ImageView reminderImgDown1;
    private TextView reminderTextDown2;
    private ImageView reminderImgDown2;
    private LinearLayout layoutText1;
    private LinearLayout layoutText2;
    private LinearLayout layoutText3;
    private LinearLayout layoutText4;
    private LinearLayout layoutImg1;
    private LinearLayout layoutImg2;
    private LinearLayout layoutDown1;
    private LinearLayout layoutDown2;
    private LinearLayout layoutVideo;


    //objects
    private ReminderDao reminderDao;
    private DayDao dayDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        mapObjectsFromView();
        instanceObjects();

        try {
            Reminder reminder;
            reminder = (Reminder) this.getIntent().getExtras().get(PARAM_SHOW_REMINDER);
            if (reminder != null) {
                Log.d("REMINDER OPENNED", reminder.getName());
                if ((reminder.getDay() == null) || reminder.getDay().getType().equals(DayType.TEMPLATE)) {
                    insertReminderIntoHistory(reminder);
                } else if (reminder.getDay().getType().equals(DayType.HISTORY)) {

                }
            } else {
                Log.d("REMINDER OPENNED", "NULL");
            }


            setReminderOnView(reminder);
        } catch (Exception e) {
            Log.e("ReminderActivity", "onCreate", e);
        }
    }

    private void insertReminderIntoHistory(Reminder reminder) {
        //Inserindo o dia
        reminder.getDay().setType(DayType.HISTORY);

        reminder.getDay().setId(dayDao.insert(reminder.getDay()));

        reminder.setBeenViewed(LocalDateTime.now());
        reminder.setLastView(LocalDateTime.now());

        reminder.setId(reminderDao.insert(reminder));

    }

    private void instanceObjects() {
        reminderDao = new ReminderDao(this);
        dayDao = new DayDao(this);
    }

    /**
     * Seta os dados do reminder nos componentes da tela
     *
     * @param r
     */
    private void setReminderOnView(Reminder r) {
        if (r != null) {
            setText(reminderText1, r.getText1(), layoutText1);
            setText(reminderText2, r.getText2(), layoutText2);
            setText(reminderText3, r.getText3(), layoutText3);
            setText(reminderText4, r.getText4(), layoutText4);
            setImage(reminderImg1, r.getImagePath1(), layoutImg1);
            setImage(reminderImg2, r.getImagePath2(), layoutImg2);
            setVideo(reminderVideo, r.getVideoPath(), layoutVideo);

            boolean textdown1 = true;
            if (!validateStr(r.getText1()) && !validateStr(r.getImagePath1())) {
                reminderTextDown1.setVisibility(TextView.GONE);
                reminderImgDown1.setVisibility(ImageView.GONE);
                layoutDown1.setVisibility(LinearLayout.GONE);
                textdown1 = false;
            }

            boolean textdown2 = true;
            if (!validateStr(r.getText2()) && !validateStr(r.getImagePath2())) {
                reminderTextDown2.setVisibility(TextView.GONE);
                reminderImgDown2.setVisibility(ImageView.GONE);
                layoutDown2.setVisibility(LinearLayout.GONE);
                textdown2 = false;
                if (!validateStr(r.getImagePath1())) {
                    reminderTextDown1.setVisibility(TextView.GONE);
                    reminderImgDown1.setVisibility(ImageView.GONE);
                    layoutDown1.setVisibility(LinearLayout.GONE);
                    textdown1 = false;
                }
            }

            if (!validateStr(r.getText3()) && !validateStr(r.getText4())
                    && !validateStr(r.getVideoPath())) {
                reminderTextDown2.setVisibility(TextView.GONE);
                reminderImgDown2.setVisibility(ImageView.GONE);
                layoutDown2.setVisibility(LinearLayout.GONE);
                if (!textdown2) {
                    reminderTextDown1.setVisibility(TextView.GONE);
                    reminderImgDown1.setVisibility(ImageView.GONE);
                    layoutDown1.setVisibility(LinearLayout.GONE);
                }
            }

        }
    }

    private void setText(TextView textView, String text, LinearLayout linearLayout) {
        if ((text != null) && (text.length() > 0)) {
            textView
                    .setText(getStringByName(text));
        } else {
            linearLayout.setVisibility(TextView.GONE);
            textView.setVisibility(TextView.GONE);
        }
    }

    private void setImage(ImageView imageView, String image, LinearLayout linearLayout) {
        if ((image != null) && (image.length() > 0)) {
            int id = getResources()
                    .getIdentifier(Constants.APP_PACKAGE_NAME + ":" + image, null, null);

            imageView.setImageResource(id);
        } else {
            linearLayout.setVisibility(TextView.GONE);
            imageView.setVisibility(ImageView.GONE);
        }
    }

    private void setVideo(VideoView videoView, String video, LinearLayout linearLayout) {
        if ((video != null) && (video.length() > 0)) {
            int id = getResources()
                    .getIdentifier(video, "raw", Constants.APP_PACKAGE_NAME);

            Uri path = Uri.parse("android.resource://" + Constants.APP_PACKAGE_NAME + "/" + id);
            videoView.setVideoURI(path);
            videoView.start();
        } else {
            videoView.setVisibility(VideoView.INVISIBLE);
            layoutVideo.setVisibility(LinearLayout.GONE);
        }
    }

    private String getStringByName(String name) {
        int id = getResources().getIdentifier(name, "string", getPackageName());
        return getString(id);
    }


    private void mapObjectsFromView() {
        reminderText1 = (TextView) findViewById(R.id.reminder_text1);
        reminderText2 = (TextView) findViewById(R.id.reminder_text2);
        reminderText3 = (TextView) findViewById(R.id.reminder_text3);
        reminderText4 = (TextView) findViewById(R.id.reminder_text4);
        reminderImg1 = (ImageView) findViewById(R.id.reminder_img1);
        reminderImg2 = (ImageView) findViewById(R.id.reminder_img2);
        reminderVideo = (VideoView) findViewById(R.id.reminder_video);
        reminderTextDown1 = (TextView) findViewById(R.id.reminder_text_down1);
        reminderImgDown1 = (ImageView) findViewById(R.id.reminder_img_down1);
        reminderTextDown2 = (TextView) findViewById(R.id.reminder_text_down2);
        reminderImgDown2 = (ImageView) findViewById(R.id.reminder_img_down2);
        layoutText1 = (LinearLayout) findViewById(R.id.reminder_text1_layout);
        layoutText2 = (LinearLayout) findViewById(R.id.reminder_text2_layout);
        layoutText3 = (LinearLayout) findViewById(R.id.reminder_text3_layout);
        layoutText4 = (LinearLayout) findViewById(R.id.reminder_text4_layout);
        layoutImg1 = (LinearLayout) findViewById(R.id.reminder_img1_layout);
        layoutImg2 = (LinearLayout) findViewById(R.id.reminder_img2_layout);
        layoutDown1 = (LinearLayout) findViewById(R.id.reminder_down1_layout);
        layoutDown2 = (LinearLayout) findViewById(R.id.reminder_down2_layout);
        layoutVideo = (LinearLayout) findViewById(R.id.reminder_video_layout);
    }

    private boolean validateStr(String str) {
        return (str != null) && (str.length() > 0);
    }
}
