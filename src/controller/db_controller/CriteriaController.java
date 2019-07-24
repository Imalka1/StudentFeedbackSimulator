package controller.db_controller;

import db.DBConnection;
import model.Criteria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CriteriaController {

    public List<Criteria> getCriteriaHeadings() {
        List<Criteria> criterias = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            Statement createStatement = connection.createStatement();
            ResultSet rst = createStatement.executeQuery("select echid,text from evaluation_criteria_heading");
            while (rst.next()) {
                Criteria criteria = new Criteria();
                criteria.setEchid(rst.getInt(1));
                criteria.setCriteriaHeadingName(rst.getString(2));
                criterias.add(criteria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return criterias;
    }

    public List<Criteria> getCriterias(Criteria criteria) {
        List<Criteria> criterias = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select ecid,text from evaluation_criteria where echid=?");
            preparedStatement.setObject(1, criteria.getEchid());
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Criteria criteriaObj = new Criteria();
                criteriaObj.setEcid(rst.getInt(1));
                criteriaObj.setCriteriaName(rst.getString(2));
                criterias.add(criteriaObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return criterias;
    }
}
