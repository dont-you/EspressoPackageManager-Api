package com.espresso.api.servlets.handlers;

import com.espresso.api.exceptions.PassedParametersException;
import com.espresso.api.tables.Table;

public final class DeleteParametersControl extends ParametersControl{
    public void performCheckout(Table entry,String id) throws PassedParametersException {
        if(!id.matches("^[\\d]+$")){

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("wrong id");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            throw new PassedParametersException("id is wrong",5, entry.getTableName());
        }
    }
}
