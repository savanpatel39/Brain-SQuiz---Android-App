package com.example.savan.brainsquiz;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Savan on 15-11-14.
 */
public class Questions
{
    private String question[];
    private String answer[];
    private String options[][];
    public String getQuestion;
    public String getAnswer;
    public String getOptions[] = new String[4];

    private String q[] = new String[] {
                "In which decade was Rolls-Royce Limited founded?",
                "Which car manufacturer acquired Rolls-Royce in 2003?",
                "Which car has the fastest 0-60 time?",
                "What type of wood does Rolls-Royce traditionally use for its dashboards?",
                "In what year did Rolls-Royce sell Bentley to another auto manufacturer?",
                "How much horsepower does a 2010 Rolls-Royce Ghost Sedan have?",
                "Where is the 2014 Rolls-Royce Wraith assembled?",
                "In what year did Rolls-Royce acquire Bentley?",
                "Which Rolls-Royce model was NOT sold during the 1980's?",
                "The 1998-2002 Rolls-Royce Silver Seraph is closely related to which vehicle?"
    };

    private String a[] = new String[]{
            "1910's",
            "BMW",
            "2011 Rolls-Royce Ghost",
            "Walnut",
            "1998",
            "563",
            "Goodwood, England",
            "1931",
            "Silver Dawn",
            "Bentley Arnage"
    };


    private String o[][] = new String[][]{
            {"1890's","1900's","1920's","1910's"},
            {"Fiat","General Motors","Volkswagen","BMW"},
            {"2011 Rolls-Royce Ghost","1995 Rolls-Royce Silver Spirit","2009 Rolls-Royce Phantom Coupe","1995 Rolls-Royce Flying Spur"},
            {"Walnut","Cherry","Poplar","Mahogany"},
            {"2001","2000","1998","2003"},
            {"390","618","453","563"},
            {"Goodwood, England","Crewe, England","Liverpool, England","Derby, England"},
            {"1920","1945","1931","1984"},
            {"Silver Shadow","Silver Dawn","Silver Spirit","Corniche II"},
            {"Bentley Continental GT","Acura RL","Bentley Arnage","Audi A8L"}
        };


    public Questions()
    {
        question = new String[q.length];
        answer = new String[a.length];
        options = new String[10][4];

        for (int i = 0; i < q.length; i++)
        {
            question[i] = q[i];
            answer[i] = a[i];

            for( int j = 0 ; j < 4 ; j++ )
            {
                    options[i][j] = o[i][j];
            }
        }
    }

    public Questions(String getQuestion,String getAnswer,String getOptions[])
    {
        this.getQuestion = getQuestion;
        this.getAnswer = getAnswer;
        this.getOptions = getOptions;
    }

    public int[] randomizeQuestions()
    {
        Random r = new Random();
        int index[] = new int[5];
        ArrayList<Integer> idx = new ArrayList<Integer>();
        int i=0;

        while ( idx.size()!=5 )
        {
            int temp = (r.nextInt(10));

            if(!idx.contains(temp))
            {
                index[i++] = temp;
                idx.add(temp);
            }
        }
        return index;
    }

    public Questions getQuestionDetails(int index)
    {
        return new Questions(question[index],answer[index],options[index]);
    }
}
