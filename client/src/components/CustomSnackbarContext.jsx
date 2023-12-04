import React, { createContext, useContext, useState } from "react";
import CustomSnackbar from "./CustomSnackbar";

const SnackbarContext = createContext();

export const SnackbarProvider = ({ children }) => {
  const [snackbarConfig, setSnackbarConfig] = useState({
    open: false,
    message: "",
    severity: "success",
  });

  const showSnackbar = (message, severity = "success") => {
    setSnackbarConfig({
      open: true,
      message,
      severity,
    });
  };

  const hideSnackbar = () => {
    setSnackbarConfig((prevConfig) => ({
      ...prevConfig,
      open: false,
    }));
  };

  return (
    <SnackbarContext.Provider value={{ showSnackbar, hideSnackbar }}>
      {children}
      <CustomSnackbar {...snackbarConfig} onClose={hideSnackbar} />
    </SnackbarContext.Provider>
  );
};

export const useSnackbar = () => {
  return useContext(SnackbarContext);
};
