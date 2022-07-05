package mongodb.demo.app.application;

import mongodb.demo.app.domain.Hospital;

public interface HospitalSaveService {

    /**
     * 신규 동물병원 등록 후 반환합니다.
     *
     * @param hospital 사용자 입력 데이터
     * @return 등록된 동물병원
     */
    Hospital saveHospital(HospitalSaveRequest hospital);

}
