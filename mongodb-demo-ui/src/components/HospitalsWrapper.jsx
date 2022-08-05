import TravelExploreIcon from "@mui/icons-material/TravelExplore";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemSecondaryAction from "@mui/material/ListItemSecondaryAction";
import { IconButton, List, ListItemText } from "@mui/material";

const HospitalItem = ({ hospital, selectedHospital, setSelectedHospital }) => {
  return (
    <ListItemButton
      divider
      selected={hospital.id === selectedHospital?.id}
      onClick={() => setSelectedHospital(hospital)}
    >
      <ListItemSecondaryAction
        children={
          <IconButton
            onClick={() =>
              window.open(
                `https://map.naver.com/v5/search/${encodeURI(hospital.name)}`,
                "_blank"
              )
            }
          >
            <TravelExploreIcon />
          </IconButton>
        }
      />
      <ListItemText
        primary={hospital.name}
        {...(hospital.distance && {
          secondary: `${hospital.distance.value.toFixed(2)}km`,
        })}
      />
    </ListItemButton>
  );
};

function HospitalsWarpper({ loading, hospitals, ...props }) {
  if (loading) return <span>loading...</span>;
  return (
    <List>
      {hospitals?.map((hospital) => {
        return (
          <HospitalItem key={hospital.id} hospital={hospital} {...props} />
        );
      })}
    </List>
  );
}

export default HospitalsWarpper;
