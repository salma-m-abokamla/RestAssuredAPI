package com.apitesting.enums;

import lombok.Getter;

public enum UFLAPIS {

    REGISTERED_NUMBERS("RegisteredNumbers"),
    SEND_OTAC("SendOTAC"),
    GET_ACCOUNTS("GetAccounts"),
    GET_SUBSCRIPTIONS_LIST("GetSubscriptionsList"),
    UPFORNT_LOGIN("UpFrontLogin");

    private UFLAPIS (String name) {
        this.name = name;
    }

    @Getter
    private String name;
}
