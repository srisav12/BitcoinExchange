package com.bitcoinexchange.splash_screen.utils;

import com.bitcoinexchange.splash_screen.dashboard_screen.TransactionPojo;

import java.util.ArrayList;
import java.util.List;



public class SampleDataClass {

    String [] name = {"Airbitz","ANX","BIPS","BitGo","Airbitz","ANX","BitPay","BitGo","Airbitz","BitPay","BitGo","BitPay","ANX"};
    String [] date = {"January 24, 2003","May 19, 2003","September 22, 2004","September 30, 2005","April 26, 2007","July 17, 2007","April 21, 2008","May 7, 2008","March 25, 2009","March 18, 2010","June 18, 2010","June 18, 2012"};
    String [] amt = {"145","594","196","830","430","617","788","367","859","869","850","101"};
    int [] sndRcv = {1,2,1,2,1,1,2,1,2,1,2,2};


    public List<TransactionPojo> getListData(){
        List<TransactionPojo> list = new ArrayList<>();
        for(int i=0; i<12; i++){
            TransactionPojo item = new TransactionPojo();
            item.setCompanyName(name[i]);
            item.setDate(date[i]);
            item.setAmount(amt[i]);
            item.setSendReceive(sndRcv[i]);

            list.add(item);
        }

        return list;
    }
}
