import Container from "./containers/Container";
import Header from "./containers/Header";
import SuccessToast from "./components/SuccessToast";

import { useState } from "react";


function App() {
  const [messageToast, setMessageToast] = useState({status: false, message: ""})

  return (
    <div className="App min-h-screen bg-slate-50 dark:bg-black dark:text-white pb-1">
      <Header />
      <Container setMessageToast={setMessageToast} />
      <SuccessToast messageToast={messageToast} setMessageToast={setMessageToast}/>
    </div>
  );
}

export default App;
