/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthit.dslservice.dao;

import com.healthit.dslservice.Filter;
import com.healthit.dslservice.dto.KephLevel;
import com.healthit.dslservice.dto.adminstrationlevel.Facility;
import com.healthit.dslservice.dto.dhis.Indicator;
import com.healthit.dslservice.util.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author duncan
 */
public class DhisDao {

    final static Logger log = Logger.getLogger(FacilityDao.class);
    private StringBuilder getALlFaciltiesBuilder = new StringBuilder(
            "Select \"Indicator ID\" as id,\"Indicator name\" as indicatorName,code as kmflcode,value as kpivalue,"
            + " \"Group name\" as indicatorGroup, startdate, enddate "
            + "from vw_mohdsl_dhis where code is not null "
    );

    public List<Indicator> getIndicators(
            List<String> idicatorName,
            List<String> indicatorGroup,
            String startDate,
            String endDate,
            List<String> mflCode
    ) {

        if (idicatorName != null) {
            if (!idicatorName.isEmpty()) {
                Iterator i = idicatorName.iterator();
                String append = "";
                int count = 0;
                while (i.hasNext()) {
                    if (count == 0) {
                        append = " and indicatorName=" + (String) i.next() + " ";
                        count = count + 1;
                    } else {
                        append = " or indicatorName=" + (String) i.next() + " ";
                    }
                }
                getALlFaciltiesBuilder.append(append);
            }
        }

        if (indicatorGroup != null) {
            if (!indicatorGroup.isEmpty()) {
                Iterator i = indicatorGroup.iterator();
                String append = "";
                int count = 0;
                while (i.hasNext()) {
                    if (count == 0) {
                        append = " and indicatorGroup=" + (String) i.next() + " ";
                        count = count + 1;
                    } else {
                        append = " or indicatorGroup=" + (String) i.next() + " ";
                    }
                }
                getALlFaciltiesBuilder.append(append);
            }
        }

        if (mflCode != null) {
            if (!mflCode.isEmpty()) {
                Iterator i = mflCode.iterator();
                String append = "";
                int count = 0;
                while (i.hasNext()) {
                    if (count == 0) {
                        append = " and code=" + (String) i.next() + " ";
                        count = count + 1;
                    } else {
                        append = " or code=" + (String) i.next() + " ";
                    }
                }
                getALlFaciltiesBuilder.append(append);
            }
        }

        Filter filter = new Filter();
        String orderBy = " order by startdate,enddate ";
        getALlFaciltiesBuilder.append(orderBy + " OFFSET " + filter.getOffset() + " " + " LIMIT " + filter.getLimit());

        List<Indicator> indicatorList = new ArrayList();
        ResultSet rs = Database.executeQuery(getALlFaciltiesBuilder.toString());
        log.info("Fetching facilities");
        try {
            while (rs.next()) {
                Indicator indicator = new Indicator();
                indicator.setEndDate(rs.getString("enddate"));
                indicator.setId(rs.getString("id"));
                indicator.setIdicatorName(rs.getString("indicatorName"));
                indicator.setIndicatorGroup(rs.getString("indicatorGroup"));
                indicator.setIndicatorValue(rs.getString("kpivalue"));
                indicator.setMflCode(rs.getString("kmflcode"));
                indicator.setStartDate(rs.getString("startdate"));
                indicatorList.add(indicator);
            }
        } catch (SQLException ex) {
            log.error(ex);
        }
        return indicatorList;
    }

}