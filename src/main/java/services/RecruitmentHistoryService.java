package services;

import daoImplementaion.RecruitmentHistoryDAOImp;
import entities.RecruitmentHistory;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RecruitmentHistoryService {

    public  RecruitmentHistoryDAOImp recruitmentHistoryDAOImp;

    public RecruitmentHistoryService(RecruitmentHistoryDAOImp instance) {
        recruitmentHistoryDAOImp = instance;
    }

    public  RecruitmentHistory assignAnEmployeeToAnAgency(RecruitmentHistory recruitmentHistory) {

        try {
            Optional<RecruitmentHistory> recruitmentHistoryOptional = recruitmentHistoryDAOImp.assignAnEmployeeToAnAgency(recruitmentHistory);

            if (recruitmentHistoryOptional.isPresent()) {
                return recruitmentHistoryOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<RecruitmentHistory> findAll() {

        try {
            List<RecruitmentHistory> recruitmentHistories = recruitmentHistoryDAOImp.findAll().orElse(Collections.emptyList());

            if (recruitmentHistories.isEmpty()) {
                throw new Exception("No Recruitment Histories Found!");
            } else {
                return recruitmentHistories;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
