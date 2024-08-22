package com.oceanTech.L1.dto.response;

import java.util.List;

public class ProvinceResponse {
        private String province;
        private List<String> districts;

        // Getters and Setters
        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public List<String> getDistricts() {
            return districts;
        }

        public void setDistricts(List<String> districts) {
            this.districts = districts;
        }

}
