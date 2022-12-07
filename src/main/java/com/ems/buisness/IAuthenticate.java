package com.ems.buisness;

import java.net.HttpCookie;

public interface IAuthenticate {
    public HttpCookie login() throws Exception;
    public HttpCookie logout() throws Exception;
}
