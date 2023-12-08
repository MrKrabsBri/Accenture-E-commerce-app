import "./App.css";
import React from "react";
import { SnackbarProvider } from "./components/CustomSnackbarContext";
import Routes from "./routes";
import { AuthProvider } from "./auth/AuthContext";

const App = () => {
  return (
    <AuthProvider>
      <SnackbarProvider>
        <Routes />
      </SnackbarProvider>
    </AuthProvider>
  );
};

export default App;
