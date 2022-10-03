import { Link, NavLink } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg bg-dark navbar-dark">
      <div className="container-fluid">
        <Link className="navbar-brand d-block d-lg-none" to="/">
          Trusted Reviews
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <div className="container-fluid navbar-nav me-auto mb-2 mb-lg-0">
            <div className="col-xl-1 col-lg-2 nav-item d-none d-lg-block">
              <Link
                className="navbar-brand align-middle"
                style={{ top: "4px", position: "relative" }}
                to="/">

                Trusted Reviews

                <img src="logo4.png" className="col-xl-4 col-lg-5" width="40px" height="40px"></img>

              </Link>
            </div>
            <div className="col-xl-10 col-lg-9">
              <div className="row justify-content-center">
                <div className="col-1 nav-item">
                  <NavLink
                    className={({ isActive }) =>
                      isActive ? "nav-link active" : "nav-link"
                    }
                    to="/"
                    exact={true}
                  >
                    Home
                  </NavLink>
                </div>
                <div className="col-1 nav-item">
                  <NavLink
                    className={({ isActive }) =>
                      isActive ? "nav-link active" : "nav-link"
                    }
                    to="/about"
                  >
                    About
                  </NavLink>
                </div>
                <div className="col-xl-2 col-lg-3 nav-item">
                  <NavLink
                    className={({ isActive }) =>
                      isActive ? "nav-link active" : "nav-link"
                    }
                    to="/trustedreviews"
                  >
                    Trusted Reviews
                  </NavLink>
                </div>
              </div>
            </div>
            <div className="col-1 text-end nav-item">
              <NavLink
                className={({ isActive }) =>
                  isActive ? "nav-link active" : "nav-link"
                }
                to="/login"
              >
                Sign-In
              </NavLink>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;