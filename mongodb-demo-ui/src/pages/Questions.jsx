import { useNavigate, useParams } from "react-router-dom";
import { Box, Typography } from "@mui/material";
import CustomButton from "../components/CustomButton";

const questions = [
  {
    question: "나의 반려견의 나이는 2살(18개월) ~ 8살 이다.",
    nextPath: "/questions/2",
    resultPath: "/results/impossible",
  },
  {
    question: "나의 반려견의 몸무게는 20kg 이상이다.",
    nextPath: "/questions/3",
    resultPath: "/results/impossible",
  },
  {
    question:
      "나의 반려견은 매달 심장사상충과 내외부구충 예방약을 먹이며 정기적으로 종합 백신(항체가 검사)을 하고 있다.",
    nextPath: "/questions/4",
    resultPath: "/results/impossible",
  },
  {
    question:
      "나의 반려견은 심장사상충, 바베시아, 혈액관련 질병, 바이러스관련 질병을 앓은 이력이 없다.",
    nextPath: "/questions/5",
    resultPath: "/results/impossible",
  },
  {
    question:
      "나의 반려견은 출산 경험이 없으며, 중성화 수술을 한지 6개월이 넘었다.",
    nextPath: "/results/possible",
    resultPath: "/results/impossible",
  },
];

const Question = ({ question, nextPath, resultPath }) => {
  let navigate = useNavigate();
  return (
    <Box
      width="100%"
      height="100vh"
      display="flex"
      justifyContent="center"
      flexDirection="column"
      alignItems="center"
    >
      <Typography
        variant="h6"
        margin={3}
        textAlign="center"
        fontWeight="600"
        sx={{ wordBreak: "keep-all" }}
      >
        {question}
      </Typography>
      <Box display="flex" justifyContent="center" gap="1rem">
        <CustomButton color="red" onClick={() => navigate(resultPath)}>
          아니오
        </CustomButton>
        <CustomButton color="blue" onClick={() => navigate(nextPath)}>
          예
        </CustomButton>
      </Box>
    </Box>
  );
};

function Questions() {
  const { id } = useParams();
  const { question, nextPath, resultPath } = questions[id - 1];
  return (
    <Question question={question} nextPath={nextPath} resultPath={resultPath} />
  );
}

export default Questions;
