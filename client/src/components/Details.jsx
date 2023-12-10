import React from "react";
import { useLocation } from "react-router-dom";
import { Grid, Typography, Paper, Box } from "@mui/material";

const Details = () => {
  const location = useLocation();
  const { item } = location.state;

  return (
    <Box
      p={10}
      minHeight="100vh"
      display="flex"
      alignItems="center"
      justifyContent="center"
    >
      <Grid container spacing={4} justifyContent="center">
        <Grid item xs={12} md={6} alignSelf="center">
          <Box display="flex" alignItems="center" justifyContent="center">
            <img
              src={item.itemImage}
              alt={item.itemName}
              style={{ width: "auto", height: "80vh", borderRadius: "8px" }}
            />
          </Box>
        </Grid>
        <Grid item xs={12} md={6}>
          <Box style={{ padding: "20px", textAlign: "center" }}>
            <Typography variant="h4" gutterBottom>
              {item.itemName}
            </Typography>
            <Typography variant="body1" paragraph>
              {item.description}
            </Typography>
            <Typography variant="body2">Size: {item.size}</Typography>
            <Typography variant="body2">
              Quantity Available: {item.quantityAvailable}
            </Typography>
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
};

export default Details;
