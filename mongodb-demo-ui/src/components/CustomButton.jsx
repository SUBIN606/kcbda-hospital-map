import ButtonUnstyled from "@mui/base/ButtonUnstyled";
import { styled } from "@mui/system";

const colors = {
  blue: { 500: "#3985f1", 600: "#0072E5" },
  red: { 500: "#ef4040", 600: "#d82727" },
  mint: { 500: "#0fc9cf", 600: "#0bb4b9" },
};

const StyledButton = styled(ButtonUnstyled)`
  font-weight: bold;
  ${({ fontSize }) => {
    return fontSize ? `font-size: ${fontSize};` : "font-size: 0.875rem;";
  }};
  padding: 12px 24px;
  border-radius: 8px;
  color: white;
  transition: all 150ms ease;
  cursor: pointer;
  border: none;
  &:disabled {
    background-color: #cccccc;
    cursor: not-allowed;
  }

  ${({ color }) => {
    return `background-color: ${colors[color][500]}; &:hover {background-color: ${colors[color][600]};}`;
  }}
`;

const CustomButton = ({ children, ...rest }) => {
  return <StyledButton {...rest}>{children}</StyledButton>;
};

export default CustomButton;
