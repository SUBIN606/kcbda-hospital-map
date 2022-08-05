import { Typography, Box } from "@mui/material";
import { Link } from "react-router-dom";
import CustomButton from "./CustomButton";

const CommonResult = () => {
  return (
    <>
      <Typography variant="caption" color={"GrayText"} gutterBottom>
        홈페이지에서 헌혈견과 헌혈견 협회에 대해 더 자세히 알 수 있어요.
      </Typography>

      <CustomButton
        sx={{ margin: 1 }}
        color="mint"
        fontSize="12pt"
        onClick={() =>
          window.open("https://cafe.naver.com/kcbda2017", "_blank")
        }
      >
        한국 헌혈견 협회 홈페이지 방문하기
      </CustomButton>

      <Link to={"/"}>
        <Typography
          variant="caption"
          sx={{ marginTop: 2, textDecoration: "underline" }}
          color={"GrayText"}
        >
          처음으로 돌아가기
        </Typography>
      </Link>
    </>
  );
};

export default CommonResult;
