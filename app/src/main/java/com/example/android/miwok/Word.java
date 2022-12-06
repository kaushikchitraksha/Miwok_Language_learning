package com.example.android.miwok;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mAudio;
    private int mImage = NO_IMAGE;
    private static final int NO_IMAGE = -1;

    // Here we are creating a constructor for assigning the values of variables

    public Word(String DefaultTranslation,String MiwokTranslation,int Image, int Audio){
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mImage = Image;
        mAudio = Audio;
    }
    public Word(String DefaultTranslation,String MiwokTranslation,int Audio){
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mAudio = Audio;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }
    public int getmAudio(){
        return mAudio;
    }
    public int getImage(){return mImage;}

    public boolean hasImage(){
        if(mImage != -1){
            return true;
        }
        else{
            return false;
        }
    }

}
