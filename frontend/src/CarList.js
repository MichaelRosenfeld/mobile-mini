import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavBar';
import { Link } from 'react-router-dom';

class CarList extends Component {

    constructor(props) {
        super(props);
        this.state = {cars: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/cars')
            .then(response => response.json())
            .then(data => this.setState({cars: data}));
    }

    async remove(id) {
        await fetch(`/cars/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedCars = [...this.state.cars].filter(i => i.id !== id);
            this.setState({cars: updatedCars});
        });
    }

    render() {
        const {cars, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const carList = cars.map(car => {
            return <tr key={car.id}>
                <td style={{whiteSpace: 'nowrap'}}>{car.make}</td>
                <td>{car.model}</td>
                <td>{car.constructionYear}</td>
                <td>{car.description}</td>
                <td>{car.price}</td>
                <td>{car.email}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/cars/" + car.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(car.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/cars/new">Add new Car</Button>
                    </div>
                    <h3>Cars</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Make</th>
                            <th width="30%">Model</th>
                            <th width="30%">Year constructed</th>
                            <th width="30%">Description</th>
                            <th width="30%">Price</th>
                            <th width="30%">Email</th>
                            <th width="40%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {carList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );

    }
}



export default CarList;