const userController = {
    getUser(req, res) {
        const id = req.params.id;

        res.status(200).json({
            status: "success",
            data: `User: ${id}`,
        });
    },
};

export default userController;
