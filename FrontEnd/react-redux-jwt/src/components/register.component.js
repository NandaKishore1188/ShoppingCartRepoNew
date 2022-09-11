import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";

import { connect } from "react-redux";
import { register } from "../actions/auth";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};



const fullName = (value) => {
  if (value.length < 3 || value.length > 30) {
    return (
      <div className="alert alert-danger" role="alert">
        The full name must be between 3 and 20 characters.
      </div>
    );
  }
};

const emailId = (value) => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const vpassword = (value) => {
  if (value.length < 6 || value.length > 40) {
    return (
      <div className="alert alert-danger" role="alert">
        The password must be between 6 and 40 characters.
      </div>
    );
  }
};


// const vpassword1 = (value) => {
//   if (vpassword1!==vpassword) {
//     return (
//       <div className="alert alert-danger" role="alert">
//         Both passwords are not same!!
//       </div>
//     );
//   }
// };

const mobileNumber = (value) => {
  if ( value.length !== 10) {
    return (
      <div className="alert alert-danger" role="alert">
        The mobile number must be 10 digits!!
      </div>
    );
  }
};




class Register extends Component {
  constructor(props) {
    super(props);
    this.handleRegister = this.handleRegister.bind(this);
    this.onChangeFullName = this.onChangeFullName.bind(this);
    this.onChangeEmailId = this.onChangeEmailId.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.onChangeMobileNumber=this.onChangeMobileNumber.bind(this);
    this.onChangeGender=this.onChangeGender.bind(this);

    this.state = {
      fullName: "",
      emailId: "",
      password: "",
      mobileNumber:"",

      successful: false,
    };
  }

  onChangeFullName(e) {
    this.setState({
      fullName: e.target.value,
    });
  }

  onChangeEmailId(e) {
    this.setState({
      emailId: e.target.value,
    });
  }

  onChangePassword(e) {
    this.setState({
      password: e.target.value,
    });
  }
  onChangeMobileNumber(e) {
    this.setState({
      mobileNumber: e.target.value,
    });
  }

  onChangeGender(e) {
    this.setState({
      gender: e.target.value,
    });
  }
  handleRegister(e) {
    e.preventDefault();

    this.setState({
      successful: false,
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      this.props
        .dispatch(
          register(this.state.fullName, this.state.emailId, this.state.password,this.state.mobileNumber)
        )
        .then(() => {
          this.setState({
            successful: true,
          });
        })
        .catch(() => {
          this.setState({
            successful: false,
          });
        });
    }
  }

  render() {
    const { message } = this.props;

    return (

      <div className="col-md-12">
        <div className="card bg-light text-dark">

          <h1><center>User Registration </center></h1>


          <Form
            onSubmit={this.handleRegister}
            ref={(c) => {
              this.form = c;
            }}
          >
            {!this.state.successful && (
              <div>
                <div className="form-group">
                  <label htmlFor="fullName">Full Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="fullName"
                    value={this.state.fullName}
                    onChange={this.onChangeFullName}
                    validations={[required, fullName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="emailId">Email</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="emailId"
                    value={this.state.emailId}
                    onChange={this.onChangeEmailId}
                    validations={[required, emailId]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="password">Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="password"
                    value={this.state.password}
                    onChange={this.onChangePassword}
                    validations={[required, vpassword]}
                  />
                </div>

                {/* <div className="form-group">
                  <label htmlFor="password">Retype Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="password"
                    value={this.state.password}
                    onChange={this.onChangePassword}
                    validations={[required, vpassword1]}
                  />
                </div> */}

                <div className="form-group">
                  <label htmlFor="mobileNumber">Mobile</label>
                  <Input
                    type="mobileNumber"
                    className="form-control"
                    name="mobileNumber"
                    value={this.state.mobileNumber}
                    onChange={this.onChangeMobileNumber}
                    validations={[required, mobileNumber]}
                  />
                </div>

                <div className="form-group">
                <label htmlFor="gender">Gender</label>
                <select value={this.state.gender} onChange={this.handleSubmit}> 
                  <option name="male"> Male</option>
                  <option name="female">Female</option>
                  <Input
                    type="gender"
                    className="form-control"
                    name="gender"
                    value={this.state.mobileNumber}
                    onChange={this.onChangeMobileNumber}
                  />
              </select>
                </div>



                <div className="form-group">
                  <button className="btn btn-dark btn-block">Sign Up</button>
                </div>
              </div>
            )}

            {message && (
              <div className="form-group">
                <div className={this.state.successful ? "alert alert-success" : "alert alert-danger"} role="alert">
                  {message}
                </div>
              </div>
            )}
            <CheckButton
              style={{ display: "none" }}
              ref={(c) => {
                this.checkBtn = c;
              }}
            />
          </Form>
        </div>
      </div>
    );
  }
}

function mapStateToProps(state) {
  const { message } = state.message;
  return {
    message,
  };
}

export default connect(mapStateToProps)(Register);
