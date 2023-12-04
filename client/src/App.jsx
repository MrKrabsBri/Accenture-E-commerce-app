import "./App.css";
import React from "react";
import { SnackbarProvider } from "./components/CustomSnackbarContext";
import Routes from "./routes";

const App = () => {
  return (
    <SnackbarProvider>
      <div>
        <Routes />
      </div>
    </SnackbarProvider>
  );
};

export default App;
