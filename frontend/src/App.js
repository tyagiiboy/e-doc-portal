import Container from "./containers/Container";
import Header from "./containers/Header";
import SuccessToast from "./components/SuccessToast";
import Footer from "./containers/Footer";

import { useState } from "react";


function App() {

  const [messageToast, setMessageToast] = useState({status: false, message: ""})

  return (
    <div className="App min-h-screen bg-slate-50 dark:bg-black dark:text-white">
      <Header />
      <Container setMessageToast={setMessageToast}  />
      <SuccessToast 
        messageToast={messageToast}
        setMessageToast={setMessageToast}/>
      <Footer />
    </div>
  );
}

export default App;
