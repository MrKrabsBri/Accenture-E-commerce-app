import React, { useState, useEffect } from "react";
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  IconButton,
  Stack,
  Menu,
  MenuItem,
} from "@mui/material";
import { Link } from "react-router-dom";
import MenuIcon from "@mui/icons-material/Menu";

const Navbar = () => {
  const [anchorEl, setAnchorEl] = useState(null);
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = localStorage.getItem("authenticatedUser");
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);

  const handleMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    localStorage.removeItem("authenticatedUser");
    localStorage.removeItem("jwtToken");
    setUser(null);
    setAnchorEl(null);
  };

  const renderMenuItems = () => {
    return [
      user ? (
        <MenuItem key="logout" onClick={handleLogout}>
          Logout
        </MenuItem>
      ) : (
        <Stack key="auth-options">
          <MenuItem
            key="login"
            onClick={handleMenuClose}
            component={Link}
            to="/login"
          >
            Login
          </MenuItem>
          <MenuItem
            key="register"
            onClick={handleMenuClose}
            component={Link}
            to="/register"
          >
            Register
          </MenuItem>
        </Stack>
      ),
      // <MenuItem key="home" onClick={handleMenuClose} component={Link} to="/">
      //   Home
      // </MenuItem>,
      // Conditionally render Logout or Login link

      // <MenuItem
      //   key="login"
      //   onClick={handleMenuClose}
      //   component={Link}
      //   to="/login"
      // >
      //   Login
      // </MenuItem>,
      // <MenuItem
      //   key="register"
      //   onClick={handleMenuClose}
      //   component={Link}
      //   to="/register"
      // >
      //   Register
      // </MenuItem>,
      // <MenuItem
      //   key="addItem"
      //   onClick={handleMenuClose}
      //   component={Link}
      //   to="/additem"
      // >
      //   Add Item
      // </MenuItem>,
    ];
  };

  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          <Link to="/" style={{ textDecoration: "none", color: "inherit" }}>
            Ecommerce App
          </Link>
        </Typography>

        {user ? (
          <Stack direction="row" spacing={2}>
            <Typography variant="body1">Welcome, {user.username}!</Typography>
            <Button
              color="inherit"
              onClick={handleLogout}
              sx={{ display: { xs: "none", sm: "block" } }}
            >
              Logout
            </Button>
          </Stack>
        ) : (
          <Stack direction="row" spacing={2}>
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
          </Stack>
        )}

        {/* <Button
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
        </Button> */}

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
