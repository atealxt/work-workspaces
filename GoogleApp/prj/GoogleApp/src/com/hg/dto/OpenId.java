package com.hg.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OpenId {

    private final String federatedIdentity;

    private final String authDomain;

    private Set<String> attributesRequest = new HashSet<String>();

    private final String logo;

    public OpenId(final String authDomain, final String federatedIdentity, final String logo) {
        super();
        this.authDomain = authDomain;
        this.federatedIdentity = federatedIdentity;
        this.logo = logo;
    }

    public OpenId(
            final String authDomain,
            final String federatedIdentity,
            final String logo,
            final Set<String> attributesRequest) {
        super();
        this.authDomain = authDomain;
        this.federatedIdentity = federatedIdentity;
        this.logo = logo;
        this.attributesRequest = attributesRequest;
    }

    public static final List<OpenId> OPENID_PROVIDERS;

    static {
        OPENID_PROVIDERS = new ArrayList<OpenId>();
        OPENID_PROVIDERS.add(new OpenId(null, "google.com/accounts/o8/id",
                                        "http://openid.net/images/get-logos/google.png"));
        OPENID_PROVIDERS.add(new OpenId("yahoo.com", "yahoo.com",
                                        "http://openid.net/wordpress-content/uploads/2009/11/yahoo.png"));
        OPENID_PROVIDERS.add(new OpenId("myspace.com", "myspace.com",
                                        "http://openid.net/wordpress-content/uploads/2007/10/logo_myspace_s.gif"));
        OPENID_PROVIDERS.add(new OpenId("twitter.com", "twitter.com",
                                        "http://www.kerstin-wagner.com/design/twitter_logo.jpg"));
        OPENID_PROVIDERS.add(new OpenId("facebook.com", "facebook.com",
                                        "http://www.boogie-knights.org/images/facebook_logo.jpg"));
        // TODO t.sina, qq, renren
    }

    public String getFederatedIdentity() {
        return federatedIdentity;
    }

    public String getAuthDomain() {
        return authDomain;
    }

    public Set<String> getAttributesRequest() {
        return attributesRequest;
    }

    public String getLogo() {
        return logo;
    }
}
