package com.ems.emailAnalytics.buisness;

import com.ems.bulkEmail.buisness.EmailDetails;
import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;

public interface Analytics {
    public boolean performAnalytics(String analyticsId, EmailDetails emailDetails, IEmailDetailsPersistence emailDetailsPersistence);
}
