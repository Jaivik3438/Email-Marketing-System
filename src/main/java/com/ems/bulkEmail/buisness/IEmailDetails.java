package com.ems.bulkEmail.buisness;

import com.ems.bulkEmail.persistence.IEmailDetailsPersistence;

public interface IEmailDetails {

    public void saveEmailDetails(IEmailDetailsPersistence persist,IEmailDetails emailDetails);
}
