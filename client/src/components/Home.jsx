import React from "react";
import { Box, Button, Container } from "@mui/material";
import ItemList from "./ItemList";

const Home = () => {
  return (
    <Container>
      <Box component="header" sx={{ py: 3, px: 3 }}>
        <Button variant="contained" color="success" size="large">
          Add New Product
        </Button>
      </Box>
      <ItemList />
    </Container>
  );
};

export default Home;
