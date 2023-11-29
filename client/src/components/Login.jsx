import React from "react";
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

const Login = () => {
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
          <form>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="username">Username</InputLabel>
                  <OutlinedInput id="username" label="Username" />
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="email">Email</InputLabel>
                  <OutlinedInput id="email" label="Email" type="email" />
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <FormControl fullWidth variant="outlined">
                  <InputLabel htmlFor="password">Password</InputLabel>
                  <OutlinedInput
                    id="password"
                    label="Password"
                    type="password"
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
