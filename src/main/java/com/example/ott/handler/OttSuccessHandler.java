package com.example.ott.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class OttSuccessHandler implements OneTimeTokenGenerationSuccessHandler {

    OneTimeTokenGenerationSuccessHandler handler = new RedirectOneTimeTokenGenerationSuccessHandler("/redirect-ott-page");
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken oneTimeToken) throws IOException, ServletException {
        UriComponentsBuilder token = UriComponentsBuilder.fromHttpUrl(UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath())
                .path("login/ott").queryParam("token", oneTimeToken.getTokenValue());
        System.out.println(token.toUriString());
        handler.handle(request, response, oneTimeToken);
    }
}
