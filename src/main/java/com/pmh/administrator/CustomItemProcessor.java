package com.pmh.administrator;

import com.pmh.administrator.model.Report;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Report, Report> {

    public Report process(Report item) throws Exception {

        System.out.println("Processing..." + item);
        String fname = item.getFirstName();
        String lname = item.getLastName();

        item.setFirstName(fname.toUpperCase());
        item.setLastName(lname.toUpperCase());
        return item;
    }
}
