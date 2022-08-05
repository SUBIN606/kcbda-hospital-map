import { useState } from "react";
import axios from "axios";
import Map from "./Map";
import useScript from "../hooks/useScript";
import useAsync from "../hooks/useAsync";
import HospitalsWarpper from "./HospitalsWrapper";
import { Box, Grid, useMediaQuery } from "@mui/material";
import CustomButton from "./CustomButton";

const getHospitals = async (x = null, y = null) => {
  const res = await axios.get(
    `${process.env.REACT_APP_BASE_URL}/hospitals${
      x && y ? `?x=${x}&y=${y}` : ""
    }`
  );
  return res.data;
};

function HospitalMap() {
  const [selectedHospital, setSelectedHospital] = useState(null);
  const [closestSortLoading, setClosestSortLoading] = useState(false);
  const { loading, data, error, execute } = useAsync(getHospitals, true);

  const matches = useMediaQuery("(max-width:1000px)");
  const mobile = useMediaQuery("(max-width:600px)");

  const status = useScript(
    `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${process.env.REACT_APP_NCP_CLIENT_KEY}`
  );

  const handleMarkerClick = (data) => {
    setSelectedHospital(data);
  };

  const handleSuccess = (position) => {
    const {
      coords: { latitude, longitude },
    } = position;

    execute(longitude, latitude);
    setClosestSortLoading(false);
  };

  const handleError = (e) => {
    console.log(e);
    setClosestSortLoading(false);
  };

  const handleOnClick = () => {
    setClosestSortLoading(true);
    navigator.geolocation.getCurrentPosition(handleSuccess, handleError);
  };

  return (
    <Grid
      container
      flexDirection={matches ? "column-reverse" : "row"}
      justifyContent="center"
      alignItems="center"
    >
      <Grid
        item
        width={mobile ? "100%" : "500px"}
        height={500}
        sx={{ overflowX: "auto" }}
      >
        <Box
          position={"sticky"}
          top={0}
          paddingTop={1}
          sx={{ backgroundColor: "white", zIndex: 10 }}
        >
          <CustomButton
            color="mint"
            type="button"
            onClick={handleOnClick}
            disabled={loading || closestSortLoading}
          >
            거리순
          </CustomButton>
        </Box>

        <Box>
          <HospitalsWarpper
            loading={loading || closestSortLoading}
            hospitals={data}
            selectedHospital={selectedHospital}
            setSelectedHospital={(hospital) => setSelectedHospital(hospital)}
          />
        </Box>
      </Grid>

      <Grid width={mobile ? "100%" : "500px"}>
        {status === "ready" && (
          <Map
            markers={data}
            handleMarkerClick={handleMarkerClick}
            center={selectedHospital?.location}
            width={mobile ? "100%" : "500px"}
            height="500px"
          />
        )}
      </Grid>
    </Grid>
  );
}
export default HospitalMap;
