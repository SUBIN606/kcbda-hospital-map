import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Main from "../pages/Main";
import Questions from "../pages/Questions";
import Impossible from "../pages/results/Impossible";
import Possible from "../pages/results/Possible";

function Root() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Main />} />
        <Route path="/questions/:id" element={<Questions />} />
        <Route exact path="/results/possible" element={<Possible />} />
        <Route exact path="/results/impossible" element={<Impossible />} />
      </Routes>
    </Router>
  );
}

export default Root;
