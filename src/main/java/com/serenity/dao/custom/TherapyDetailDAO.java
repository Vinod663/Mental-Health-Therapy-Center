package com.serenity.dao.custom;

import com.serenity.dao.CrudDAO;
import com.serenity.entity.TherapyDetail;
import com.serenity.entity.TherapyDetailId;

import java.sql.SQLException;


public interface TherapyDetailDAO extends CrudDAO<TherapyDetail, TherapyDetailId> {
    boolean saveWithReferences(TherapyDetail therapyDetail, int therapistId, String programId) throws SQLException;
}
