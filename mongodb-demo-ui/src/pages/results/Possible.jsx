import { Box, Divider, Typography } from "@mui/material";
import CommonResult from "../../components/CommonResult";
import HospitalMap from "../../components/HospitalMap";

function Possible() {
  return (
    <Box
      padding={2}
      paddingTop={10}
      paddingBottom={10}
      width="100%"
      height="100%"
      display="flex"
      flexDirection="column"
      justifyContent="center"
      alignItems="center"
    >
      <Typography
        textAlign="center"
        variant="h4"
        fontWeight="800"
        gutterBottom
        sx={{ wordBreak: "keep-all" }}
      >
        🐶 헌혈견의 조건을 모두 충족하고 있어요! 🐶
      </Typography>
      {/* <iframe
        width="560"
        height="315"
        src="https://www.youtube.com/embed/mHHZGGrBT-8"
        title="YouTube video player"
        frameBorder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowFullScreen
      ></iframe> */}
      <Typography sx={{ wordBreak: "keep-all" }}>
        헌혈견이 되어 공혈견을 없애는 데 함께 하면 어떨까요?
      </Typography>
      <CommonResult />

      <Box paddingTop={5} paddingBottom={2} textAlign="center">
        <Typography variant="h5" fontWeight={700}>
          헌혈견 협회 공식 협력 병원 목록이에요.
        </Typography>
        <Typography sx={{ wordBreak: "keep-all" }}>
          모든 헌혈은 공식 협력 병원에서 진행돼요. 가까운 협력 병원을
          찾아보세요.
        </Typography>
      </Box>

      <HospitalMap />
    </Box>
  );
}

export default Possible;
