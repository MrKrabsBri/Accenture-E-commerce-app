import React from "react";
import { Box, Button, Container } from "@mui/material";
import ItemList from "./ItemList";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const navigate = useNavigate();

  const handleAddItem = () => {
    navigate("/additem");
  };

  return (
    <Container>
      <Box component="header" sx={{ py: 3, px: 3 }}>
        <Button
          variant="contained"
          color="success"
          size="large"
          onClick={handleAddItem}
        >
          Add New Product
        </Button>
      </Box>
      <ItemList />
    </Container>
  );
};

export default Home;
