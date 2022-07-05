package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;

/** 신규 동물병원 등록 요청 */
public interface HospitalSaveRequest {

    String getName();

    double getX();

    double getY();

    default Hospital toHospital() {
        return Hospital.of(getName(), getX(), getY());
    }

}
