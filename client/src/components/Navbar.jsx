import React, { useState } from "react";
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  IconButton,
  Menu,
  MenuItem,
} from "@mui/material";
import { Link } from "react-router-dom";
import MenuIcon from "@mui/icons-material/Menu";

const Navbar = () => {
  const [anchorEl, setAnchorEl] = useState(null);

  const handleMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  const renderMenuItems = () => {
    return [
      <MenuItem key="home" onClick={handleMenuClose} component={Link} to="/">
        Home
      </MenuItem>,
      <MenuItem
        key="login"
        onClick={handleMenuClose}
        component={Link}
        to="/login"
      >
        Login
      </MenuItem>,
      <MenuItem
        key="register"
        onClick={handleMenuClose}
        component={Link}
        to="/register"
      >
        Register
      </MenuItem>,
      <MenuItem
        key="addItem"
        onClick={handleMenuClose}
        component={Link}
        to="/additem"
      >
        Add Item
      </MenuItem>,
    ];
  };

  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Ecommerce App
        </Typography>

        <IconButton
          size="large"
          edge="end"
          color="inherit"
          aria-label="menu"
          onClick={handleMenuOpen}
          sx={{ display: { xs: "block", sm: "none" } }}
        >
          <MenuIcon />
        </IconButton>

        <Button
          color="inherit"
          component={Link}
          to="/"
          sx={{ display: { xs: "none", sm: "block" } }}
        >
          Home
        </Button>
        <Button
          color="inherit"
          component={Link}
          to="/login"
          sx={{ display: { xs: "none", sm: "block" } }}
        >
          Login
        </Button>
        <Button
          color="inherit"
          component={Link}
          to="/register"
          sx={{ display: { xs: "none", sm: "block" } }}
        >
          Register
        </Button>
        <Button
          color="inherit"
          component={Link}
          to="/additem"
          sx={{ display: { xs: "none", sm: "block" } }}
        >
          Add Item
        </Button>

        <Menu
          id="responsive-menu"
          anchorEl={anchorEl}
          open={Boolean(anchorEl)}
          onClose={handleMenuClose}
        >
          {renderMenuItems()}
        </Menu>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;
