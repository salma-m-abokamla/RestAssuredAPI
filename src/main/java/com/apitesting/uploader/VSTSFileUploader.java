package com.apitesting.uploader;

import files.ConfigFileReader;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

/**
 * @author IsmaielK
 */

public class VSTSFileUploader {

    private static StringBuilder uploadableFileContent = new StringBuilder().append("{\n");

    private static final String REMOTE_URL = "https://vfuk-digital.visualstudio.com/Digital/_git/MVA10_APIs_Responces";

    // Username and password generated by the VSTS Repo
    private static final String USERNAME = "salma.elmetwally2";
    private static final String PASSWORD = "ctjib6tvi33y7qlvye4yjppyu3veq6i4yc47eqn4oomctvtrncfq";

    private static final String REST_ASSURED_API_REMOTE_URL = "https://vfuk-digital.visualstudio.com/Digital/_git/RestAssuredAPI";

    private static final String REST_ASSURED_USERNAME = "karim.ismaiel";
    private static final String REST_ASSURED_PASSWORD = "itsvs3eymvbgjl4ldn4evp6uiml7dk4jgdarypjanjt7yb3sxboq";

    public static void addResponseContentToUploadableFile(String fileContent, String apiName) {
        uploadableFileContent.append("  ").append(apiName).append(" : {\n");

        for (String s : fileContent.split("\n")) {
            uploadableFileContent.append("         ").append(s).append("\n");
        }

        uploadableFileContent.append("  }\n");
    }

    public static void pushToRemote(String suiteName) throws IOException, GitAPIException {
        // Create a temporary folder to clone the repo in it
        File localPath = File.createTempFile("GitRepository", "");
        if (!localPath.delete()) {
            throw new IOException("Could not delete temporary file " + localPath);
        }

        // Clone the repo into the created temporary folder
        out.println("Cloning from " + REMOTE_URL + " to " + localPath);

        try (Git git = Git.cloneRepository()
                .setURI(REMOTE_URL)
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(USERNAME, PASSWORD))
                .setDirectory(localPath)
                .call()) {

            Repository repository = git.getRepository();
            // get the directory path from the configuration file
            ConfigFileReader.readProperityFile();
            String directoryPath = getLatestReleaseBranch() + "/" + ConfigFileReader.properties.getProperty("deploymentEnv")
                    + "/" + ConfigFileReader.properties.getProperty("tilEnv");
            // check if the path exists and if not build it
            File theDir = buildDirectoryIfNotExists(repository, directoryPath);

            // create the file

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("__yyyy_MM_dd__HH_mm_ss");
            LocalDateTime now = LocalDateTime.now();

            File myFile = new File(theDir, suiteName + dtf.format(now) +".json");
            out.println(myFile.getPath());
            myFile.createNewFile();

            // Stage all files in the repo including new files
            git.add().addFilepattern(".").call();

            // and then commit the changes.
            git.commit().setMessage("Test results for ... has passed with no failures and successfully uploaded").call();

            try (PrintWriter writer = new PrintWriter(myFile)) {
                uploadableFileContent.append("}");
                writer.append(uploadableFileContent);
            }
//             Stage all changed files, omitting new files, and commit with one command
            git.commit()
                    .setAll(true)
                    .setMessage("Commit changes to all files")
                    .call();
            git.add().addFilepattern("*").call();
            RevCommit result = git.commit().setMessage("initial commit").call();
            git.push()
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(USERNAME, PASSWORD))
                    .call();
            out.println("Pushed with commit: " + result);
            uploadableFileContent = new StringBuilder();
            uploadableFileContent.append("{\n");
        }
    }

    private static String getLatestReleaseBranch() throws IOException, GitAPIException {
        File localPath = File.createTempFile("RestAssuredRepo", "");

        if (!localPath.delete()) {
            throw new IOException("Could not delete temporary file " + localPath);
        }

        try (Git git = Git.cloneRepository()
                .setURI(REST_ASSURED_API_REMOTE_URL)
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(REST_ASSURED_USERNAME, REST_ASSURED_PASSWORD))
                .setDirectory(localPath)
                .call()) {

            List<Ref> call = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();

            Pattern p = Pattern.compile("([0-9]*\\.[0-9]+)");   // the pattern to search for

            Matcher matcher;

            List<Double> sprintNumbers = new ArrayList<>();

            for (Ref ref : call) {
                matcher = p.matcher(ref.getName());
                if (matcher.find())
                    sprintNumbers.add(Double.parseDouble(matcher.group(1)));
            }

            Collections.sort(sprintNumbers);

            return !sprintNumbers.isEmpty() ? String.valueOf(sprintNumbers.get(sprintNumbers.size() - 1)) : "";
        }
    }


    private static File buildDirectoryIfNotExists(Repository repository, String directoryPath){
        File theDir = new File(repository.getDirectory().getParent(), directoryPath);
        if (!theDir.exists()) {
            List<String> strings = Arrays.asList(directoryPath.split("/"));
            StringBuilder previousDirectory = new StringBuilder();
            for (String string : strings) {
                File newDir = new File(repository.getDirectory().getParent() + previousDirectory, string);
                if(!newDir.exists())
                    newDir.mkdir();
                previousDirectory.append("/").append(string);
            }
        }
        return theDir;
    }

}

