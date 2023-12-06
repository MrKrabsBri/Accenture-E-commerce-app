import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  Typography,
  Button,
  Grid,
  Paper,
  Container,
  Box,
  FormControl,
  InputLabel,
  OutlinedInput,
} from "@mui/material";
import { loginUser } from "../services/api";
import { useSnackbar } from "../components/CustomSnackbarContext";

const Login = () => {
  const navigate = useNavigate();
  const { showSnackbar } = useSnackbar();
  const [loginData, setLoginData] = useState({
    username: "",
    password: "",
    email: "",
  });

  const [loading, setLoading] = useState(false);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setLoginData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);

    try {
      const response = await loginUser(loginData);

      if (response && response.status === 200) {
        const token = response.headers.authorization;
        const authenticatedUser = response.data;

        console.log("User authenticated successfully:", authenticatedUser);
        console.log("Token:", token);

        showSnackbar("Signed in successfully", "success");
        navigate("/");
        setLoginData({
          username: "",
          password: "",
          email: "",
        });
      } else {
        showSnackbar("Check your credentials and try again...", "error");
        console.error("Incomplete user data received");
      }
    } catch (error) {
      showSnackbar("Server issue... Try again later...", "error");
      console.error("Error logging in:", error);
      setError("Server issue... Try again later.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container component="main" maxWidth="xs">
      <Box
        display="flex"
        flexDirection="column"
        alignItems="center"
        minHeight="100vh"
      >
        <Box mt={4} mb={4}>
          <Typography variant="h4" align="center">
            Login
          </Typography>
        </Box>
        <Paper elevation={3} style={{ padding: "20px", width: "100%" }}>
          <form onSubmit={handleSubmit}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="username">Username</InputLabel>
                  <OutlinedInput
                    id="username"
                    label="Username"
                    name="username"
                    value={loginData.username}
                    onChange={handleInputChange}
                  />
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="password">Password</InputLabel>
                  <OutlinedInput
                    id="password"
                    label="Password"
                    type="password"
                    name="password"
                    value={loginData.password}
                    onChange={handleInputChange}
                  />
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <Button variant="contained" color="primary" type="submit">
                  Login
                </Button>
              </Grid>
            </Grid>
          </form>
        </Paper>
      </Box>
    </Container>
  );
};

export default Login;
