package com.ems.userSegment.persistence;

import com.ems.userSegment.model.UserSegment;

public interface IUserSegmentPersistent {
    UserSegment getUserSegmentByUserId(String userId);
}
