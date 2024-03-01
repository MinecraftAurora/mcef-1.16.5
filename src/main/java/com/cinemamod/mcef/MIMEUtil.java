package com.cinemamod.mcef;

public final class MIMEUtil {
    public static String mimeFromExtension(String ext) {
        // TODO: might want to port https://github.com/CinemaMod/mcef/blob/master-1.19.2/src/main/resources/assets/mcef/mime.types
        //       adding cases to the switch isn't the most convenient thing
//        ext = ext.toLowerCase(Locale.US);
//        String ret = mimeTypeMap.get(ext);
//        if (ret != null)
//            return ret;

        // If the mimeTypeMap couldn't be loaded, fall back to common things
        switch (ext) {
            case "htm":
            case "html": return "text/html";
            case "css": return "text/css";
            case "pdf": return "application/pdf";
            case "xz": return "application/x-xz";
            case "tar": return "application/x-tar";
            case "cpio": return "application/x-cpio";
            case "7z": return "application/x-7z-compressed";
            case "zip": return "application/zip";
            case "js": return "text/javascript";
            case "json": return "application/json";
            case "jsonml": return "application/jsonml+json";
            case "jar": return "application/java-archive";
            case "ser": return "application/java-serialized-object";
            case "class": return "application/java-vm";
            case "wad": return "application/x-doom";
            case "png": return "image/png";
            case "jpg":
            case "jpeg": return "image/jpeg";
            case "gif": return "image/gif";
            case "svg": return "image/svg+xml";
            case "xml": return "text/xml";
            case "txt": return "text/plain";
            case "oga":
            case "ogg":
            case "spx": return "audio/ogg";
            case "mp4":
            case "mp4v":
            case "mpg4": return"video/mp4";
            case "m4a":
            case "mp4a": return "audio/mp4";
            case "mid":
            case "midi":
            case "kar":
            case "rmi": return "audio/midi";
            case "mpga":
            case "mp2":
            case "mp2a":
            case "mp3":
            case "mp3a":
            case "m2a": return "audio/mpeg";
            case "mpeg":
            case "mpg":
            case "mpe":
            case "m1v":
            case "m2v": return "video/mpeg";
            case "jpgv": return "video/jpeg";
            case "h264": return "video/h264";
            case "h261": return "video/h261";
            case "h263": return "video/h263";
            case "webm": return "video/webm";
            case "flv": return "video/flv";
            case "m4v": return "video/m4v";
            case "qt":
            case "mov": return "video/quicktime";
            case "ogv": return "video/ogg";
            default: return null;
        }
    }
}
