import { Typography, Box } from "@mui/material";
import CommonResult from "../../components/CommonResult";
import HospitalMap from "../../components/HospitalMap";

function Impossible() {
  return (
    <Box
      padding={2}
      paddingTop={10}
      paddingBottom={10}
      width="100%"
      height="100%"
      display="flex"
      justifyContent="center"
      flexDirection="column"
      alignItems="center"
    >
      <Typography
        textAlign="center"
        variant="h4"
        fontWeight="800"
        gutterBottom
        sx={{ wordBreak: "keep-all" }}
      >
        헌혈견의 조건과 맞지 않는 부분이 있어요.
      </Typography>
      <Typography
        textAlign="center"
        fontWeight={700}
        sx={{ wordBreak: "keep-all" }}
      >
        하지만 헌혈견을 응원하고, 공혈견을 없애는데 함께하는건 어떨까요?
      </Typography>

      <CommonResult />

      <Box paddingTop={5} paddingBottom={2} textAlign="center">
        <Typography variant="h5" fontWeight={700}>
          헌혈견 협회 공식 협력 병원 목록이에요.
        </Typography>
        <Typography variant="caption" sx={{ wordBreak: "keep-all" }}>
          만약 나의 반려견이 수혈이 필요할 경우 협력 병원에서 진료 후 수혈을
          받을 수 있다고 해요.
        </Typography>
        <Typography sx={{ wordBreak: "keep-all" }}>
          가까운 협력 병원을 찾아보세요.
        </Typography>
      </Box>

      <HospitalMap />
    </Box>
  );
}

export default Impossible;
