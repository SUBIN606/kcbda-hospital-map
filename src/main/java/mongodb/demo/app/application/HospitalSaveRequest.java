package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;
import mongodb.demo.app.domain.HospitalDocument;

/** 신규 동물병원 등록 요청 */
public interface HospitalSaveRequest {

    String getName();

    double getX();

    double getY();

    /** HospitalDocument로 변환 */
    default HospitalDocument toHospitalDocument() {
        return HospitalDocument.of(getName(), getX(), getY());
    }

    /** Hospital 엔티티로 변환 */
    default Hospital toHospital() {
        return Hospital.of(getName(), getX(), getY());
    }

}
