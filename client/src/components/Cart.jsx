import React, { useState } from "react";
import {
  Typography,
  Grid,
  Paper,
  Container,
  Box,
  Divider,
  IconButton,
  Dialog,
  DialogTitle,
  DialogActions,
  Button,
} from "@mui/material";
import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";
const Cart = () => {
  const cartItem = {
    quantity: 10,
    name: "Beautiful dress",
    price: 10,
    size: "XL",
    desc: "This is a beautiful dress, worn only once.",
    imageUrl:
      "https://media.macphun.com/img/uploads/customer/how-to/579/15531840725c93b5489d84e9.43781620.jpg?q=85&w=1340",
  };
  const [openDialog, setOpenDialog] = useState(false);
  const handleDeleteClick = () => {
    setOpenDialog(true);
  };

  const handleConfirmDelete = () => {
    setOpenDialog(false);
  };

  const handleCancelDelete = () => {
    setOpenDialog(false);
  };
  return (
    <Container component="main" maxWidth="md">
      <Box
        display="flex"
        flexDirection="column"
        alignItems="center"
        mt={4}
        mb={4}
      >
        <Dialog open={openDialog} onClose={handleCancelDelete}>
          <DialogTitle>Delete Item</DialogTitle>
          <DialogTitle>
            {"Are you sure you want to delete this item?"}
          </DialogTitle>
          <DialogActions>
            <Button onClick={handleCancelDelete} color="primary">
              Cancel
            </Button>
            <Button onClick={handleConfirmDelete} color="error">
              Delete
            </Button>
          </DialogActions>
        </Dialog>
        <Paper elevation={3} style={{ padding: "20px", width: "100%" }}>
          <Grid container alignItems="center">
            <Grid item xs={12}>
              <Grid container spacing={1}>
                <Grid item xs={12} sm={3}>
                  <img
                    src={cartItem.imageUrl}
                    alt={cartItem.name}
                    style={{
                      width: "80%",
                      height: "auto",
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={3}>
                  <Typography
                    variant="h6"
                    style={{
                      fontWeight: "bold",
                    }}
                  >
                    {cartItem.name}
                  </Typography>
                  <Typography variant="body1">Size: {cartItem.size}</Typography>
                  <Typography variant="body1">
                    In Stock: {cartItem.quantity}
                  </Typography>
                  <br />
                  <Typography variant="body1">
                    <strong>Description:</strong>
                  </Typography>
                  <Typography variant="body1">{cartItem.desc}</Typography>
                </Grid>
                <Grid item xs={12} sm={3}>
                  <Box pt={3.9}>
                    <Typography variant="body1">
                      Price: {cartItem.price}$ each
                    </Typography>
                    <Typography variant="body1">
                      Quantity: {cartItem.quantity}{" "}
                    </Typography>
                  </Box>
                </Grid>
                <Grid item xs={6} sm={1}>
                  <Divider orientation="vertical" />
                </Grid>
                <Grid item xs={12} sm={2}>
                  <Box display="flex" alignItems="center" pt={10}>
                    <Typography variant="body1">
                      <strong>
                        Total: ${cartItem.price * cartItem.quantity}
                      </strong>
                    </Typography>
                    <IconButton onClick={handleDeleteClick}>
                      <DeleteOutlineIcon color="error" />
                    </IconButton>
                  </Box>
                </Grid>
              </Grid>

              <Divider
                style={{
                  width: "100%",
                  marginTop: "20px",
                  marginBottom: "10px",
                }}
              />
            </Grid>
          </Grid>
        </Paper>
      </Box>
    </Container>
  );
};

export default Cart;
