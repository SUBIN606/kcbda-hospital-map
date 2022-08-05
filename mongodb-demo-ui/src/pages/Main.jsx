import { Box, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import CustomButton from "../components/CustomButton";

function Main() {
  const navigate = useNavigate();
  return (
    <Box
      padding={2}
      width="100%"
      height="100vh"
      display="flex"
      flexDirection="column"
      justifyContent="center"
      alignItems="center"
    >
      <Box textAlign="center" marginBottom={2} sx={{ wordBreak: "keep-all" }}>
        <Typography variant="h6" fontWeight="800">
          반려견도 헌혈을 할 수 있다는 사실 알고 계신가요?
        </Typography>
        <Typography variant="caption" component="p">
          우리나라의 약 600만 마리의 반려견의 혈액 공급은 300여 마리의
          "공혈견"에 의존하고 있습니다.
        </Typography>
        <Typography variant="caption" component="p">
          공혈견은 나이가 들어 채혈할 수 없을 때까지 열악한 환경에서 채혈만을
          위해 길러집니다.
        </Typography>
        <Typography variant="caption" component="p">
          보호자와 반려견들의 참여로 공혈견의 희생을 줄일 수 있습니다.
        </Typography>
      </Box>

      <Box textAlign="center" sx={{ wordBreak: "keep-all" }}>
        <Typography
          textAlign="center"
          variant="h5"
          fontWeight="800"
          gutterBottom
        >
          나의 반려견은 헌혈견이 될 수 있을까?
        </Typography>

        <CustomButton
          fontSize="14pt"
          color="mint"
          onClick={() => navigate("/questions/1")}
        >
          알아보기
        </CustomButton>
      </Box>
    </Box>
  );
}

export default Main;
